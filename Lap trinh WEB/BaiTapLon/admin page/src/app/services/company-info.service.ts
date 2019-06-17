import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {IFormLogin} from '../interfaces/IFormLogin';
import {IResponse} from '../interfaces/IResponse';
import {Router} from '@angular/router';
import {API_END_POINT} from '../configs/Config';
import {IUserRaw} from '../interfaces/user/IUserRaw';
import {Observable} from 'rxjs';


@Injectable({
    providedIn: 'root'
})

export class CompanyInfoService {
    urlAPI = '';

    constructor(private http: HttpClient, private router: Router) {
        this.urlAPI = API_END_POINT + 'company-info.php?action=';
    }

    get() {
        const url = this.urlAPI + 'get';
        return this.http.get<IResponse>(url);
    }

    change(formCompanyInfoChange: any) {
        const url = this.urlAPI + 'change';
        return this.http.post<IResponse>(url, formCompanyInfoChange);
    }
}
