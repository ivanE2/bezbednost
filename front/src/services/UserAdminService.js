import { request } from "../base/HTTP";
import HttpMethod from "../constants/HttpMethod";

export async function getUsers(data) {
    return await request('/api/users/', data);
}

export async function addUser(data) {
    return await request('/api/users', data, HttpMethod.POST);
}