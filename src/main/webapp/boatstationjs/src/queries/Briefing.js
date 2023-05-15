import { URL, _axios } from "./RequestConfig";


export class Briefing {
    static async getBriefingTypes() {
        const response = await _axios.get(`${URL}/api/briefing/types`);
        return response;
    }

    static async createBriefingLog(data) {
        const response = await _axios.post(`${URL}/api/briefing`, data)
        return response;
    }

    static async getBriefings() {
        const response = await _axios.get(`${URL}/api/briefing`)
        return response.data;
    }

    static async getReportedBriefings() {
        const response = await _axios.get(`${URL}/api/briefing/reported`);
        return response;
    }

    static async downloadReportTemplate(){
        const response = await _axios.get(`${URL}/api/briefing/report/template`, {responseType: "blob"});
        console.log(response);
        return response;
    }


    static async downloadReport(briefingLogId) {
        const response = await _axios.get(`${URL}/api/briefing/${briefingLogId}/report/download`, {responseType: "blob"});
        return response;
    }


    static async getBriefingLogIdByRentalLogId(rentalLogId) {
        const response = await _axios.get(`${URL}/api/briefing/id/rental/${rentalLogId}`);
        return response;
    }

    static async uploadReport(rentalLogId, scanData) {
        console.log("uploading Report")
        var formData = new FormData()
        formData.append("scan", scanData)
        for (var [key, value] of formData.entries()) { 
            console.log(key, value);
          }
        const response = await _axios.post(`${URL}/api/briefing/rental/${rentalLogId}/report/upload`, formData);
        return response;
    }
}