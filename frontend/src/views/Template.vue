<template>
    <div class="main">
        <h2 class="title">{{title}}
        </h2>

                          <!--주차장명
          주소
          전화번호
          주차현황 정보 제공여부
          주차 가능 차량 수
          유무료구분
          //한 컬럼
          기본 주차 요금
          기본 주차 시간(분 단위)
          추가 단위 요금
          추가 단위 시간(분 단위)
          일 최대 요금
          주차장 위치 좌표 위도
          주차장 위치 좌표 경도
          주차가능여부-->
        <div>
            <input type="text" id="searchAddr" name="searchAddr" placeholder="주소 검색" v-model="addr">
            <input type="text" id="searchTel" name="searchTel" placeholder="전화번호 검색" v-model="tel">
            <input type="text" id="searchParkingName" name="searchParkingName" placeholder="주차장 검색" v-model="parkingName">
            <button type="button" id="searchBtn" @click="getParkingListData">조회</button>
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
<!--                <th scope="row">주차현황 정보 제공여부</th>-->
                <th scope="row">주차 가능 차량 수</th>
                <th scope="row">유무료구분</th>
                <th scope="row">요금(분)</th>
                <th scope="row">위도/경도</th>
                <th scope="row"></th> //가능여부
                </thead>
                <tbody>
                <tr v-for="p in parkingListData">
                    <td>{{p.PARKING_NAME}}</td>
                    <td>{{p.ADDR}}</td>
                    <td>{{p.TEL}}</td>
<!--                    <td>{{p.QUE_STATUS_NM}}</td>-->
                    <td>{{p.CAPACITY}}</td>
                    <td>{{p.PAY_NM}}</td>
                    <td>{{p.RATES}}({{p.TIME_RATE}})</td>
                    <td>{{p.LAT}} / {{p.LNG}}</td>
                    <td>{{p.currentParkingCheck?'O':'X'}}</td>
                </tr>
                </tbody>

            </table>
            <div>

                <div class="btn-cover">
                    <button :disabled="pageNo === 0" @click="prev" class="page-btn">
                        이전
                    </button>

                    <button type="button" class="page-link" v-for="listPageNumber in pages.slice(pageNo-1, pageNo+5)" @click="onClickPageNo(listPageNumber)"> {{listPageNumber}} </button>

                    <button :disabled="pageNo >= pageCount - 1" @click="next" class="page-btn">
                        다음
                    </button>
                </div>
            </div>

        </div>

    </div>
</template>


<script>

  import ApiUtil from "../api/api.util";
  export default {
    name: "Template",
    components: {},
    created() {
      this.getParkingListData()
    },
    data() {
      return {
        title: '주차장 찾기',
        parkingListData:[],
        parkingTotCount: 0,
        addr:'',
        tel:'',
        parkingName:'',
        pages: [],
        pageNo : 1,
        pageSize: 10
      }
    },
    watch:{
      parkingListData(){
        this.setPages()
      }
    },
    computed:{

      pageCount () {
        let totalCount = this.parkingTotCount
        let listSize = this.pageSize
        let page = Math.floor(totalCount / listSize);
        if (totalCount % listSize > 0) page += 1;

        return page;
      }

    },
    methods: {
      onClickPageNo: function(pageNo){
        this.pageNo = pageNo
        this.getParkingListData()
      },
      getParkingListData: function(){

        const vm = this
        vm.$Progress.start()
        ApiUtil.post('/parking/cache/search',{
          pageNo : vm.pageNo,
          pageSize: vm.pageSize,
          addr:vm.addr,
          tel:vm.tel,
          parkingName:vm.parkingName
        }).then(response=>{
          if(response.message === undefined) {
            vm.$Progress.finish()
            vm.parkingListData = response.data.parkingLotInfoList
            vm.parkingTotCount = response.data.totalCount

          }else{
            alert('관리자에게 문의해 주십시오.')
          }
        })

      },
      setPages () {
        let numberOfPages = Math.ceil(this.parkingTotCount / this.pageSize);
        for (let index = 1; index <= numberOfPages; index++) {
          this.pages.push(index);
        }
      },
      next () {
        this.pageNo += 1;
      },
      prev () {
        this.pageNo -= 1;
      },
      initialization: function () {

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
        width: 1300px;
        margin: 60px auto;
        overflow: hidden;
    }

    h2.title {
        color: #ffc61c
    }
</style>
