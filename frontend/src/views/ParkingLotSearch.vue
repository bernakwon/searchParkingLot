<template>
    <div class="main">
        <h2 class="title">{{title}} <i class="fas fa-sync" style="cursor: pointer" @click="refreshCache=true"></i>
        </h2>
        <div>
            <div>
                <div class="main_left">
                    <button type="button" @click="switchSearchNearFlag" class="searchBtn"
                            :style="clickSortNearCheck?'color:orange':'color:black'">가까운 곳 찾기
                    </button>
                </div>
                <div class="main_common">
                    <div style="display:inline-block">
                    <label for="searchAddr"></label><input type="text" id="searchAddr" name="searchAddr" placeholder="주소 검색" v-model="addr" @change="getParkingListData"/>
                    <label for="searchTel"></label><input type="text" id="searchTel" name="searchTel" placeholder="전화번호 검색" v-model="tel" @change="getParkingListData"/>
                    <label for="searchParkingName"></label><input type="text" id="searchParkingName" name="searchParkingName" placeholder="주차장 검색"
                                                                  v-model="parkingName" @change="getParkingListData"/>
                    </div>
                    <div style="display:inline-block">
                        <button type="button" class="searchBtn" id="searchBtn" @click="getParkingListData">조회</button>
                    </div>

               </div>

                <div class="main_right">
                    <button type="button" class="searchBtn"  :style="clickSortCurrentCheck?'color:orange':'color:black;'" style="float:right;'"
                            @click="switchSortCurrentFlag">주차 가능 순으로 정렬
                    </button>

                </div>
            </div>
                <div>
                    <table class="table_width">
                        <colgroup>
                            <col width="15%">
                            <col width="20%">
                            <col width="15%">
                            <col width="15%">
                            <col width="15%">
                            <col width="15%">
                            <col width="15%">
                            <col width="15%">
                        </colgroup>
                        <thead>
                        <th scope="row">주차장명</th>
                        <th scope="row">주소</th>
                        <th scope="row">전화번호</th>
                        <th scope="row">주차 가능 차량 수</th>
                        <th scope="row">유무료구분</th>
                        <th scope="row">요금(분)</th>
                        <th scope="row"></th> <!--가능여부-->
                        </thead>
                        <tbody>
                        <tr v-for="p in parkingListData">
                            <td>{{p.PARKING_NAME}}</td>
                            <td>{{p.ADDR}}</td>
                            <td>{{p.TEL}}</td>
                            <td>{{p.CAPACITY}}</td>
                            <td>{{p.PAY_NM}}</td>
                            <td>{{p.RATES}}({{p.TIME_RATE}})</td>
                            <td>{{p.currentParkingCheck?'O':'X'}}</td>
                        </tr>
                        <tr v-if="parkingTotCount===1">
                            <td colspan="7" style="text-align: center">데이터가 없습니다.</td>
                        </tr>
                        </tbody>

                    </table>

                    <vue-ads-pagination
                            :total-items="parkingTotCount"
                            :page="pageNo"
                            :items-per-page="pageSize"

                    >
                        <template
                                slot="buttons"
                                slot-scope="props"
                        >
                            <vue-ads-page-button
                                    v-for="(button, key) in props.buttons"
                                    :key="key"
                                    v-bind="button"
                                    :class="{'bg-yellow-dark': button.active}"
                                    @page-change="pageNo = button.page"
                                    @range-change="start = button.start; end = button.end"
                            />
                        </template>
                    </vue-ads-pagination>

                </div>
            </div>
        </div>

</template>


<script>

  import ApiUtil from "../api/api.util";
  import VueAdsPagination, {VueAdsPageButton} from "vue-ads-pagination";


  export default {
    name: "ParkingLotSearch",
    components: {
      VueAdsPagination,
      VueAdsPageButton

    },
    created() {
      //title 변경
      document.title = this.title

      this.getParkingListData()
    },
    data() {
      return {
        title: '서울시 공영 주차장 Search',
        parkingListData: [],
        parkingTotCount: 0,
        addr: '',
        tel: '',
        parkingName: '',
        searchCurrentCheck: true,
        pageNo: 0,
        pageSize: 15,
        loading: true,
        start: 0,
        end: 0,
        myLat: 0,
        myLng: 0,
        clickSortNearCheck: false,
        clickSortCurrentCheck: false,
        sortDescription: 'default',
        refreshCache: true,
        refreshDate: ''
      }
    },
    watch: {
      pageNo() {
        this.getParkingListData()
      },
      refreshCache() {

        this.initPageNo()
        this.getParkingListData()
      },
      clickSortCurrentCheck() {
        const vm = this
        if (vm.clickSortCurrentCheck) {
          vm.sortDescription = 'currentParkingCheck'
        }else{
          vm.sortDescription = 'default'
        }
        setTimeout(function () {
          vm.initPageNo()
          vm.getParkingListData()
        }, 100)
      },
      clickSortNearCheck() {
        const vm = this
        if (vm.clickSortNearCheck) {
          vm.clickSearchNearLocation()

        } else {
          vm.myLat = 0
          vm.myLng = 0
        }
        setTimeout(function () {
          vm.initPageNo()
          vm.getParkingListData()
        }, 100)


      }
    },
    methods: {
      initPageNo: function () {
        this.start = 0
        this.end = 15
        this.pageNo = 0
      },
      switchSearchNearFlag: function () {
        this.clickSortNearCheck = !this.clickSortNearCheck
      },
      switchSortCurrentFlag: function () {
        this.clickSortCurrentCheck = !this.clickSortCurrentCheck
      },
      clickSearchNearLocation: function () {
        const vm = this
        vm.sortDescription = "distance"
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function (position) {
            vm.myLat = position.coords.latitude
            vm.myLng = position.coords.longitude

          }, function (error) {
            console.error(error);
          }, {
            enableHighAccuracy: true,
            maximumAge: 0,
            timeout: Infinity
          });
        } else {
          alert('GPS를 지원하지 않습니다');
        }

      },
      getParkingListData: function () {

        const vm = this


        vm.$Progress.start()
        ApiUtil.post('/parking/cache/search', {
          pageNo: vm.pageNo,
          pageSize: vm.pageSize,
          sortDescription: vm.sortDescription,
          start: (vm.pageNo * vm.pageSize) + 1,
          end: (vm.pageNo + 1) * vm.pageSize,
          addr: vm.addr,
          tel: vm.tel,
          parkingName: vm.parkingName,
          myLat: vm.myLat,
          myLng: vm.myLng,
          refreshCache: vm.refreshDate === '' ? true : vm.refreshCache,
          refreshDate: vm.refreshDate
        }).then(response => {

          if (response.data.message === undefined) {
            vm.$Progress.finish()
            vm.parkingListData = response.data.parkingLotInfoList
            vm.parkingTotCount = response.data.totalCount

            //새로고침 날짜 업데이트, 새로고침 여부 초기화
            vm.refreshDate = response.data.refreshDate
            vm.refreshCache = false;
          } else {
            alert('관리자에게 문의해 주십시오.')
          }
        })

      },
    }
  }
</script>

<style scoped>
    /*테이블 스타일*/
    .table_width {
        width: 100%;
        border-top: 1px solid #cccccc;
        font-size: 14px;
    }

    .table_width th {
        padding: 15px 20px;
        letter-spacing: -1px;
        /*background: #f4f6f9;*/
        font-size: 16px;
        font-weight: bold;
        color: #333333;
        border-bottom: 1px solid #ebebeb;
        vertical-align: middle;
        text-align: center;
        background-color: #ffc61c;
    }

    .table_width td {
        border-bottom: 1px solid #ebebeb;
        padding: 7px 20px;
        vertical-align: middle
    }

    .table_width td input[type="text"]:focus {
        background: #faffbd
    }

    .main {
        width: 90%;
        margin: 60px auto;
        overflow: hidden;
    }

    h2.title {
        color: #ffc61c
    }

    .vue-ads-bg-teal-500 {
        background-color: #ffc61c;
    }

    .searchBtn {
        display: block;
        font-size: 16px;
        color: #4d4d4d;
        background: #fff;
        font-weight: normal;
        border: 1px solid #c4c4c4;
        border-radius: 6px;

    }


    .main_left{

        display: inline-block;
        width: 30%;

    }
    .main_common{
        display: inline-block;
        width: 50%;

    }
    .main_right{

        display: inline-block;
        width: 20%;

    }

</style>
