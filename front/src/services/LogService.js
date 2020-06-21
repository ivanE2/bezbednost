import { request } from "../base/HTTP";
import HttpMethod from "../constants/HttpMethod";

export async function getLogs(data) {
    return await request('/api/logs/', data);
}