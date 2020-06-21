import { request } from "../base/HTTP";
import HttpMethod from "../constants/HttpMethod";

export async function deleteCert(data) {
    return await request('/api/certificates/' +  data.id, {}, HttpMethod.DELETE);
}

export async function getCerts(data) {
    return await request('/api/certificates/', data);
}

export async function addCert(data) {
    return await request('/api/certificates', data, HttpMethod.POST);
}

