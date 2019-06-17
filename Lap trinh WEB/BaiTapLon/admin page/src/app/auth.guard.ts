import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import {AuthService} from './services/auth.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    constructor(private authService: AuthService,
                private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        const isLogged = this.authService.isLogged();
        if (state.url === '/login') {
            if (isLogged) {
                this.router.navigate(['/']);
                return false;
            }
            return true;
        } else {
            if (!isLogged) {
                this.router.navigate(['/login']);
                return false;
            }
            return true;
        }
    }
}
