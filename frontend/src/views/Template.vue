<template>
  <div class="main">
    <h2 class="title">{{title}}
    </h2>
    <div>
      <h2>예약하기</h2>
      <form id="resevFrm">
        <table class="table_width">

          <tr>
            <th scope="row">회의실ID</th>
            <td><select name="conferenceId" id="conferenceId">
            </select></td>
          </tr>
          <tr>
            <th scope="row">예약자명</th>
            <td><input type="text" name="registedName" id="registedName"></td>
          </tr>
          <tr>
            <th scope="row">예약명</th>
            <td><input type="text" name="reservationName"
                       id="reservationName"></td>
          </tr>
          <tr>
            <th scope="row">예약일</th>
            <td><input type="text" id="reservationDate"
                       class="datepicker" name="reservationDate"></td>
          </tr>
          <tr>
            <th scope="row">예약시간</th>
            <td><input name="startTime" type="text" class="timeMasking"
                       id="startTime" maxlength=5> <input name="endTime"
                                                          type="text" class="timeMasking" id="endTime" maxlength=5>
            </td>
          </tr>
          <tr>
            <th scope="row">반복횟수</th>
            <td><input type="number" name="repetitionOfNum"
                       id="repetitionOfNum"></td>
          </tr>

        </table>
      </form>
      <button type="button" id="saveBtn">예약하기</button>
    </div>
    <div>
      <h2>예약현황</h2>
      <input type="text" id="searchDate" class="datepicker"
             name="searchDate">
      <button type="button" id="searchBtn">조회</button>
      <table class="table_width">
        <colgroup id="colg">

        </colgroup>
        <thead id="conferenceTh">

        </thead>
        <tbody id="reservationTb">

        </tbody>

      </table>

    </div>

  </div>
</template>


<script>
    export default {
        name: "template",
        data(){
            return {
              title:'템플릿'
            }
        },
        methods:{
          initialization : function() {

          },
          saveReservation : function() {

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
    text-align: left;
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
    width: 1024px;
    margin: 60px auto;
    overflow: hidden;
  }
  h2.title{
    color : #ffc61c
  }
</style>
