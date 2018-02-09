import request from '@/utils/request'

export function login(params) {
  return request({
    url: '/user/login',
    method: 'get',
    params
  })
}

export function getInfo(token) {
  return request({
    url: '/user/getUserBaseInfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
