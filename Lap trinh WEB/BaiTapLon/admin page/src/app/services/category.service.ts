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

export class CategoryService {
    urlAPI = '';

    constructor(private http: HttpClient, private router: Router) {
        this.urlAPI = API_END_POINT + 'category.php?action=';
    }

    create(formCategory: any) {
        const url = this.urlAPI + 'create';
        return this.http.post<IResponse>(url, formCategory);
    }

    list() {
        const url = this.urlAPI + 'list';
        return this.http.get<IResponse>(url);
    }

    detail(id: number) {
        const url = this.urlAPI + 'detail';
        return this.http.post<IResponse>(url, {id});
    }

    update(formCategory: any) {
        const url = this.urlAPI + 'update';
        return this.http.post<IResponse>(url, formCategory);
    }

    remove(id: number) {
        const url = this.urlAPI + 'remove';
        return this.http.post<IResponse>(url, {id});
    }
}
