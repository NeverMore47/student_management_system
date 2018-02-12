<template>
  <div class="tab-container">
    <el-tabs style='margin-top:15px;' v-model="activeName" type="border-card">
      <el-tab-pane v-for="item in tabMapOptions" :label="item.label" :key='item.key' :name="item.key">
        <template v-if="activeName == item.key &&  item.key == 'in'">
            <el-form ref="form" :model="form" label-width="120px">
                <el-form-item label="学号">
                    <span>{{ form.studentNo }}</span>
                </el-form-item>
                <el-form-item label="姓名">
                    <span>{{ form.studentRealName }}</span>
                </el-form-item>
                <el-form-item label="生日">
                    <span>{{ form.studentBirthDate | dateFilter }}</span>
                </el-form-item>
                <el-form-item label="班级">
                    <span>{{ form.studentClassId | classFilter }}</span>
                </el-form-item>
                <el-form-item label="性别">
                    <span>{{ form.studentGender | sexFilter }}</span>
                </el-form-item>
                <el-form-item label="健康状况">
                    <span>{{ form.studentHealthStatus }}</span>
                </el-form-item>
                <el-form-item label="去向">
                    <span>{{ form.studentNativePlace }}</span>
                </el-form-item>
            </el-form>
        </template>
        <template v-if="item.key == 'gr'">
            <el-form ref="form" :model="form" label-width="120px">
                <template v-for="item in grade">
                    <el-form-item :label="item.courseId | courseFilter" >
                        <span>{{ item.resultScore }}</span>
                    </el-form-item>
                </template>
            </el-form>
        </template>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { findStudentInfoDetail } from '@/api/student'
import { getToken } from '@/utils/auth'
export default {
  name: 'tab',
  data() {
    return {
      tabMapOptions: [
        { label: '学生信息', key: 'in' },
        { label: '学生成绩', key: 'gr' }
      ],
      activeName: 'in',
      form: {},
      grade: {}
    }
  },
    filters: {
        sexFilter(status) {
            const statusMap = {
                1: '男',
                2: '女',
                0: '未知'
            }
            return statusMap[status] 
        },
        courseFilter(courseid) {
            const courseMap = {
                1: '操作系统',
                2: '编译原理',
                3: '数据结构与算法',
                4: '人工智能导论'
            }
            return courseMap[courseid]
        },
        classFilter(classid) {
            const classMap = {
                1: '一班',
                2: '二班',
                3: '三班',
                4: '四班',
                5: '五班'
            }
            return classMap[classid]
        },
        dateFilter(datas) {
            var date = new Date(datas)
            var dateInfo = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
            return dateInfo
        }
  },
  created() {
      this.fetchData()
  },
  methods: {
    fetchData() {
        const tempData = {
            userId: getToken()
        }
        findStudentInfoDetail(tempData).then(response => {
            console.log(tempData)
            this.form = Object.assign({}, response.data.studentInfo)
            this.grade = Object.assign({}, response.data.resultInfo)
        })
    },
    showCreatedTimes() {
      this.createdTimes = this.createdTimes + 1
    }
  }
}
</script>

<style scoped>
  .tab-container{
    margin: 30px;
  }
</style>
