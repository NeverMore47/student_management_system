
<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="角色类型">
        <el-select v-model="form.region" placeholder="请选择角色类型">
          <el-option label="管理员" value="1"></el-option>
          <el-option label="老师" value="2"></el-option>
          <el-option label="助教" value="3"></el-option>
          <el-option label="学生" value="4"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">添加</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        region: '4'
      }
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$router.push('/usermanage/index')
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

