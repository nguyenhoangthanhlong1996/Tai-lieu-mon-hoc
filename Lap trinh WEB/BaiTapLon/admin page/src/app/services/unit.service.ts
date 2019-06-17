import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IResponse} from '../interfaces/IResponse';
import {Router} from '@angular/router';
import {API_END_POINT} from '../configs/Config';

@Injectable({
    providedIn: 'root'
})

export class UnitService {
    urlAPI = '';

    constructor(private http: HttpClient, private router: Router) {
        this.urlAPI = API_END_POINT + 'unit.php?action=';
    }

    create(formUnit: any) {
        const url = this.urlAPI + 'create';
        return this.http.post<IResponse>(url, formUnit);
    }

    list() {
        const url = this.urlAPI + 'list';
        return this.http.get<IResponse>(url);
    }

    detail(id: number) {
        const url = this.urlAPI + 'detail';
        return this.http.post<IResponse>(url, {id});
    }

    update(formUnit: any) {
        const url = this.urlAPI + 'update';
        return this.http.post<IResponse>(url, formUnit);
    }

    remove(id: number) {
        const url = this.urlAPI + 'remove';
        return this.http.post<IResponse>(url, {id});
    }
}
