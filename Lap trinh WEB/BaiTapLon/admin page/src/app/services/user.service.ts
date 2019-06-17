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

export class UserService {
    urlAPI = '';

    constructor(private http: HttpClient, private router: Router) {
        this.urlAPI = API_END_POINT + 'user.php?action=';
    }

    create(formUser: any) {
        const url = this.urlAPI + 'create';
        return this.http.post<IResponse>(url, formUser);
    }

    list() {
        const url = this.urlAPI + 'list';
        return this.http.get<IResponse>(url);
    }

    detail(username: string) {
        const url = this.urlAPI + 'detail';
        return this.http.post<IResponse>(url, {username});
    }

    changeInfo(formChangeUserInfo: any) {
        const url = this.urlAPI + 'change-info';
        return this.http.post<IResponse>(url, formChangeUserInfo);
    }

    changePassword(formChangeUserPassword: any) {
        const url = this.urlAPI + 'change-password';
        return this.http.post<IResponse>(url, formChangeUserPassword);
    }

    remove(username: string) {
        const url = this.urlAPI + 'remove';
        return this.http.post<IResponse>(url, {username});
    }
}
