import { URL, _axios } from "./RequestConfig";

class LifeSavingDevices {
    static async getLifeSavingDevices() {
        const response =  await _axios.get(`${URL}/api/admin/lsdevices`);
        return response;
    }

    static async getAllLifeSavingDevices() {
        const response = await _axios.get(`${URL}/api/admin/lsdevices/all`)
        return response.data;
    }

    static async deleteLifeSavingDeviceLog(lifeSavingDeviceLogId) {
        console.log("----debug delete-----")
        console.log(lifeSavingDeviceLogId)
        const response = await _axios.post(`${URL}/api/admin/lsdevices/${lifeSavingDeviceLogId}`)
        return response;
    }

    static async addLifeSavingDeviceLog(lifeSavingDeviceId, watercraftLogId) {
        const response = await _axios.post(`${URL}/api/admin/lsdevices/${lifeSavingDeviceId}/watercraft_log_id/${watercraftLogId}`);
        return response;
    }

    static async getLsdTypes() {
        const response = await _axios.get(`${URL}/api/admin/lsdevices/types`)
        return response;
    }

    static async createNewLifeSavingDevice(lsdTypeId) {
        const response = await _axios.post(`${URL}/api/admin/lsdevices/type/${lsdTypeId}`)
        return response;
    }

    static async getFreeLifeSavingDevices() {
        const response =  await _axios.get(`${URL}/api/admin/lsdevices/free`);
        return response;
    }
}

export default LifeSavingDevices;