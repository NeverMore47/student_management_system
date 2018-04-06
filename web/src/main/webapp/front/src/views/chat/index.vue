<template>
  <div class="app-container">
    <div class="filter-container">
     <el-select clearable class="filter-item" style="width: 130px" v-model="courseId" placeholder="选择课程">
        <el-option label="Web程序设计" value="1"></el-option>
        <el-option label="大学生就业指导" value="2"></el-option>
        <el-option label="分布式数据库应用" value="3"></el-option>
        <el-option label="数据结构课程设计" value="4"></el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
    </div>

    <figure>
      <div id="myChart" :style="{width: '100%', height: '60vh'}"></div>
    </figure>

  </div>
</template>

<script>
// 引入基本模板
let echarts = require('echarts/lib/echarts')
// 引入柱状图组件
require('echarts/lib/chart/bar')
// 引入提示框和title组件
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
// /result/avgResult ? courseId = 3
import { avgResult } from '@/api/student'
export default {
  name: 'hello',
  data() {
    return {
      courseId: undefined
    }
  },
  methods: {
    drawLine(data) {
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('myChart'))
        // 绘制图表
        var score = [0, 0, 0, 0, 0]
        for(var i = 0; i < data.length; i++) {
            switch (data[i].classId) {
                case 1:
                    score[0] = data[i].avgScore
                    break;
                case 2:
                    score[1] = data[i].avgScore
                    break;
                case 3:
                    score[2] = data[i].avgScore
                    break;
                case 4:
                    score[3] = data[i].avgScore
                    break;
                case 5:
                    score[4] = data[i].avgScore
                    break;
            }
        }
        const courseMap = {
            1: 'Web程序设计',
            2: '大学生就业指导',
            3: '分布式数据库应用',
            4: '数据结构课程设计'
        }
        myChart.setOption({
            color: ['#606c94'],
            title: { text: courseMap[data[0].courseId] + '的平均分' },
            tooltip: {},
            xAxis: {
                data: ["信管一班", "信管二班", "信管三班", "信管四班", "信管五班"]
            },
            yAxis: {

            },
            series: [{
                name: '分数',
                type: 'bar',
                data: score,
                barMaxWidth: '80',
                markLine: {
                data: [
                    {type: 'average', name: '平均值'}
                ]
                }
            }]
        })
    },
    handleFilter() {
        if(!this.courseId) {
            return
        }
        const tempData = {
            courseId: this.courseId
        }
        avgResult(tempData).then(response => {
            if(!response.success) {
                this.$notify({
                    title: '失败',
                    message: response.message,
                    type: 'error',
                    duration: 2000
                })
                return
            }
            const data = response.data.avgScoreList
            if(data.length <= 0) {
                this.$message('暂无数据')
            } else {
                this.drawLine(data)
            }
        })

    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  position: relative;
  padding: 20px;
  width: 100%;
  height:85vh;
}
</style>
