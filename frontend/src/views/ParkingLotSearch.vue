<template>
    <div class="main">
        <h2 class="title">{{title}}
        </h2>
        <div>
            <div id="searchbox">
                <button type="button" @click="clickSearchNearLocaion">가까운 곳 찾기</button>
               <!-- <input type="text" id="searchAddr" name="searchAddr" placeholder="주소 검색" v-model="addr">
                <input type="text" id="searchTel" name="searchTel" placeholder="전화번호 검색" v-model="tel">
                <input type="text" id="searchParkingName" name="searchParkingName" placeholder="주차장 검색"
                       v-model="parkingName">-->
                <input type="text" id="serachText" name="serachText" placeholder="주소, 전화번호, 주차장 검색" v-model="serachText">
                <button type="button" id="searchBtn" @click="getParkingListData">조회</button>
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
                    <!--                <th scope="row">주차현황 정보 제공여부</th>-->
                    <th scope="row">주차 가능 차량 수</th>
                    <th scope="row">유무료구분</th>
                    <th scope="row">요금(분)</th>
                    <th scope="row">위도/경도</th>
                    <th scope="row" @click="clickSortByCurrentParking"></th> <!--가능여부-->
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

                <vue-ads-pagination
                        :total-items="parkingTotCount"
                        :page="pageNo"
                        @page-change="pageChange"
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
            this.getParkingListData()
        },
        data() {
            return {
                title: '주차장 Search',
                parkingListData: [],
                parkingTotCount: 0,
                addr: '',
                tel: '',
                parkingName: '',
                searchText:'',
                searchCurrentCheck: true,
                pageNo: 0,
                pageSize: 10,
                loading: false,
                start: 0,
                end: 0,
                myLat: 0,
                myLng: 0,

            }
        },
        watch: {},
        computed: {},
        methods: {
            clickSearchNearLocaion: function () {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        alert(position.coords.latitude + ' ' + position.coords.longitude);
                        this.myLat = position.coords.latitude
                        this.myLng = position.coords.longitude
                    }, function (error) {
                        console.error(error);
                    }, {
                        enableHighAccuracy: false,
                        maximumAge: 0,
                        timeout: Infinity
                    });
                } else {
                    alert('GPS를 지원하지 않습니다');
                }
                this.getParkingListData()
            },
            pageChange: function (page) {
                console.log(page)
                this.pageNo = page;
                this.getParkingListData()
            },

            rageChange: function (start, end) {
                this.start = start;
                this.end = end;
            },
            getParkingListData: function () {

                const vm = this
                vm.$Progress.start()
                ApiUtil.post('/parking/cache/search', {
                    pageNo: vm.pageNo,
                    pageSize: vm.pageSize,
                    addr: vm.addr,
                    tel: vm.tel,
                    parkingName: vm.parkingName,
                    myLat: vm.myLat,
                    myLng: vm.myLng
                }).then(response => {
                    if (response.message === undefined) {
                        vm.$Progress.finish()
                        vm.parkingListData = response.data.parkingLotInfoList
                        vm.parkingTotCount = response.data.totalCount

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
        width: 1300px;
        margin: 60px auto;
        overflow: hidden;
    }

    h2.title {
        color: #ffc61c
    }

    .vue-ads-bg-teal-500 {
        background-color: #ffc61c;
    }
</style>
