import { URL, _axios } from "./RequestConfig";

export class WatercraftLog {
   
    static async updateTechnicalCondition(watercraftLogId, technicalConditionId) {
        const response = await _axios.post(`${URL}/api/admin/watercraft_log/${watercraftLogId}/technical_condition/${technicalConditionId}`);
        return response;
    }

    static async updateWatercraftPosition(watercraftLogId, watetId) {
        const response = await _axios.post(`${URL}/api/admin/watercraft_log/${watercraftLogId}/water/${watetId}`);
        return response;
    }

    static async updateWatercraftEntity(watercraftLogId, watercraftId) {
        const response = await _axios.post(`${URL}/api/admin/watercraft_log/${watercraftLogId}/watercraft-entity/${watercraftId}`);
        return response;
    }

    static async createOrUpdateWatercraftLog(data) {
        const response  = await _axios.post(`${URL}/api/admin/watercraft_log`, data)
        console.log(response)
        return response;
    }

    static async deleteWatercraftLog(watercraftLogId) {
        const response = await _axios.delete(`${URL}/api/admin/watercraft_log/${watercraftLogId}`);
        return response;    
    }
}