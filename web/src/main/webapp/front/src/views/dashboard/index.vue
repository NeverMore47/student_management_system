<template>
  <div class="dashboard-container">

    <div class="dashboard-text">欢迎你:{{name}}</div>
    <div class="bg"></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'dashboard',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    
    .bg{
background: url(../img/fg1.jpg) center center no-repeat;
    height: 80vh;
    background-size: 100%;
    -webkit-filter: grayscale(50%); /* Chrome, Safari, Opera */
    filter: grayscale(50%);

    
    -webkit-filter: blur(3px); /* Chrome, Opera */
       -moz-filter: blur(3px);
        -ms-filter: blur(3px);    
            filter: blur(3px);
    
    filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius=10, MakeShadow=false); /* IE6~IE9 */
    }
  }

  &-text {
    text-align: center;
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
