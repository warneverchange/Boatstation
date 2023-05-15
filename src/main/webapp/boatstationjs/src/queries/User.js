import { URL, _axios } from "./RequestConfig"

export class User {
    static async getCurrentUserData() {
        const response = await _axios.get(`${URL}/api/client/data`);
        return response;
    }

    static async deleteCurrentAccout() {
        const response = await _axios.post(`${URL}/api/client/delete`)
        return response;
    }


    static async updateCurrentUserClientData(data) {
        console.log(data);
        const response = await _axios.post(`${URL}/api/client/update`, data);
        return response;
    }
}