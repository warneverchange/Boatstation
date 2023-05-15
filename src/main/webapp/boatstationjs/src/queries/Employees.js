import { AddHomeWorkTwoTone } from "@mui/icons-material";
import { URL, _axios } from "./RequestConfig"


export class Employees {
    static async getEmployees() {
        const response = await _axios.get(`${URL}/api/employees`);
        return response;
    }

    static async fireEmployee(employeeId) {
        const response = await _axios.post(`${URL}/api/employees/${employeeId}/fire`)
        return response;
    }

    static async getEmployeesJobTitles() {
        const response = await _axios.get(`${URL}/api/employees/job_titles`)
        return response;
    }

    static async addNewEmployee(clientDataId, jobTitleId) {
        const response = await _axios.post(`${URL}/api/employees/new/${clientDataId}/job-title/${jobTitleId}`);
        return response;
    }
}