import { URL, _axios } from "./RequestConfig";
class Rental {
    static async getAllRentalStatuses() {
        const response = await _axios.get(`${URL}/api/admin/rental/statuses`)
        return response
    }

    static async updateRentalStatus(rentalLogId, rentalStatusId) {
        
        const response = await _axios.post(
            `${URL}/api/admin/rental/${rentalLogId}/rental-status/${rentalStatusId}`);
        return response
    }

    static async getAllRentalData() {
        const response = await _axios.get(`${URL}/api/admin/rental`)
        return response.data;
    }

    static async getBookedDatetimes() {
        const response = await _axios.get(`${URL}/api/rental/date-times/booked`);
        return response;
    }

    static async createNewRentalLog(data) {
        const response = await _axios.post(`${URL}/api/rental`, data)
        return response;
    }

    static async getClientDataByRentalLogId(rentalLogId) {
        const response = await _axios.get(`${URL}/api/admin/rental/${rentalLogId}/client`)
        return response;
    }
}

export default Rental;