import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {IFormLogin} from '../interfaces/IFormLogin';
import {IResponse} from '../interfaces/IResponse';
import {Router} from '@angular/router';
import {API_END_POINT} from '../configs/Config';


@Injectable({
    providedIn: 'root'
})

export class AuthService {

    constructor(private http: HttpClient, private router: Router) {
    }

    login(frmLogin: IFormLogin) {
        const url = API_END_POINT + 'auth.php?action=login';
        return this.http.post<IResponse>(url, frmLogin);
    }

    isLogged(): boolean {
        return !!localStorage.getItem('token');
    }

    getUserInfo() {
        const url = API_END_POINT + 'auth.php?action=info';
        return this.http.get<IResponse>(url);
    }

    logout() {
        localStorage.removeItem('token');
        this.router.navigate(['/login']);
    }
}
