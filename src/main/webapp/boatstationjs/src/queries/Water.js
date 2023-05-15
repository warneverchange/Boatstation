import {URL, _axios } from "./RequestConfig";

export class Water {
    static async getWaterTypes() {
        const response = await _axios.get(`${URL}/api/water_types`);
        return response;
    }

    static async getWaters() {
        const response = await _axios.get(`${URL}/api/waters`);
        return response;
    }
}