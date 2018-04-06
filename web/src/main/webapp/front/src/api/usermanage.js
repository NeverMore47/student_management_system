import request from '@/utils/request'

export function getUserList(params) {
    return request({
        url: '/user/findUserList',
        method: 'get',
        params
    })
}

export function addUser(params) {
    return request({
        url: '/user/createUser',
        method: 'get',
        params
    })
}

export function updateUser(params) {
    return request({
        url: '/user/updateUser',
        method: 'get',
        params
    })
}

export function delUser(params) {
    return request({
        url: '/user/deleteUser',
        method: 'get',
        params
    })
}

export function findUserExtendInfo(params) {
    return request({
        url: '/userExtendInfo/findUserExtendInfo',
        method: 'get',
        params
    })
}

export function saveUserExtendInfo(params) {
    return request({
        url: '/userExtendInfo/saveUserExtendInfo',
        method: 'get',
        params
    })
}
