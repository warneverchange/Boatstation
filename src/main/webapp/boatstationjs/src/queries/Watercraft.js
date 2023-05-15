import { URL, _axios } from "./RequestConfig";

export class Watercraft {
    static async getWatercraftView() {
        const response = await _axios.get(`${URL}/api/watercraft-with-technical-info`)
        return response.data;
    }
    
    static async getWatercraft() {
        const response = await _axios.get(`${URL}/api/watercraft`);
        return response;
    } 

    static async getTechnicalConditions() {
        const response = await _axios.get(`${URL}/api/technical_conditions`);
        return response;
    }

    static async getModels() {
        const response = await _axios.get(`${URL}/api/models`);
        return response;
    }

    static async getBrands() {
        const response = await _axios.get(`${URL}/api/brands`);
        return response;
    }


    static async getWatercraftNumbers() {
        const response = await _axios.get(`${URL}/api/watercraft/numbers`);
        return response;
    }

    static async getFreeWatercrafts() {
        const response = await _axios.get(`${URL}/api/watercraft/free`);
        console.log("Free watercrafts response")
        console.log(response)
        return response;
    }


}