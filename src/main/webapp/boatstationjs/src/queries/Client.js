import { URL, _axios } from "./RequestConfig"

export class Client {
    static async getClientDataById(clientDataId) {
        const response = await _axios.get(`${URL}/api/client/${clientDataId}`);
        return response; 
    }

    static async getAllClients() {
        const resposne = await _axios.get(`${URL}/api/client/all`);
        return resposne.data;
    }
}