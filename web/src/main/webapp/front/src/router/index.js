import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/studentmanage',
    component: Layout,
    children: [{
      path: 'index',
      name: 'studentmanage',
      component: () => import('@/views/studentmanage/index'),
      meta: { title: '学生管理', icon: 'table', role: [1, 2, 4, 5] }
    }]
  },
  {
    path: '/grademange',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'grademange',
        component: () => import('@/views/grademange/index'),
        meta: { title: '成绩管理', icon: 'table', role: [1, 2, 4] }
      }
    ]
  },
  {
    path: '/disciplinary',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'disciplinary',
        component: () => import('@/views/disciplinary/index'),
        meta: { title: '奖惩记录', icon: 'table', role: [1, 2, 4] }
      }
    ]
  },
  {
    path: '/usermanage',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'usermanage  ',
        component: () => import('@/views/usermanage/index'),
        meta: { title: '用户管理', icon: 'table', role: [1] }
      }
    ]
  },
  {
    path: '/dossier',
      component: Layout,
      children: [
      {
        path: 'index',
        name: 'dossier  ',
        component: () => import('@/views/dossier/index'),
      meta: { title: '档案管理', icon: 'table', role: [1, 2] }
    }]
  },
  {
    path: '/chat',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'chat  ',
        component: () => import('@/views/chat/index'),
        meta: { title: '生成图表', icon: 'table', role: [1, 2, 4] }
      }
    ]
  },
  {
    path: '/information',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'information  ',
        component: () => import('@/views/information/index'),
        meta: { title: '查看信息', icon: 'table', role: [3] }
      }
    ]
  },
  {
    path: '/userinfo',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index',
      name: 'userinfo',
      component: () => import('@/views/userinfo/index'),
      meta: { title: '个人信息', icon: 'table' }
    }]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

