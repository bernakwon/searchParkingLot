<template>
    <div>
        {{listData}}
        {{totalCount}}
        {{pageSize}}
        <div class="btn-cover">
            <button :disabled="pageNo === 0" @click="prev" class="page-btn">
                이전
            </button>

             <button type="button" class="page-link" v-for="listPageNumber in listData.slice(pageNo-1,pageNo+5)" @click="pageNo = listPageNumber "> {{listPageNumber}} </button>

            <button :disabled="pageNo >= pageCount - 1" @click="next" class="page-btn">
                다음
            </button>
        </div>
    </div>
</template>

<script>
  export default {
    name: 'pagination',
    data () {
      return {
        pageNo: 0
      }
    },
    props: {
      listData: {
        type: Array
      },
      totalCount: {
        type: Number,
        required: true,
        default:0
      },
      pageSize: {
        type: Number,
        required: false,
        default: 10
      }
    },
    methods: {
      next () {
        this.pageNo += 1;
      },
      prev () {
        this.pageNo -= 1;
      }
    },
    computed: {
      pageCount () {
        let totalCount = 15000
        let listSize = this.pageSize
        let page = Math.floor(totalCount / listSize);
        if (totalCount % listSize > 0) page += 1;

        return page;
      }
    }
  }
</script>

<style>
    button.page-link {
        display: inline-block;
    }
    button.page-link {
        font-size: 20px;
        color: #29b3ed;
        font-weight: 500;
    }
    .offset{
        width: 500px !important;
        margin: 20px auto;
    }
</style>