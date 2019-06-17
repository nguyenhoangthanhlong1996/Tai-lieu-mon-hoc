import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IResponse} from '../interfaces/IResponse';
import {Router} from '@angular/router';
import {API_END_POINT} from '../configs/Config';

@Injectable({
    providedIn: 'root'
})

export class ProductService {
    urlAPI = '';

    constructor(private http: HttpClient, private router: Router) {
        this.urlAPI = API_END_POINT + 'product.php?action=';
    }

    create(formProduct: any) {
        const url = this.urlAPI + 'create';
        return this.http.post<IResponse>(url, formProduct);
    }

    list() {
        const url = this.urlAPI + 'list';
        return this.http.get<IResponse>(url);
    }

    detail(id: number) {
        const url = this.urlAPI + 'detail';
        return this.http.post<IResponse>(url, {id});
    }

    update(formProduct: any) {
        const url = this.urlAPI + 'update';
        return this.http.post<IResponse>(url, formProduct);
    }

    remove(id: number) {
        const url = this.urlAPI + 'remove';
        return this.http.post<IResponse>(url, {id});
    }
}
