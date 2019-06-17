import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {
    constructor(private router: Router) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem('token');
        const tokenizeReq = req.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'application/json',
                Accept: 'application/json'
            }
        });
        return next.handle(tokenizeReq).pipe(
            tap(evt => {
                if (evt instanceof HttpResponse) {
                    const body = evt.body;
                    if (body.code === 401) {
                        localStorage.removeItem('token');
                        this.router.navigate(['/login']);
                    }
                }
            }),
            catchError((err: any) => {
                if (err instanceof HttpErrorResponse) {
                    if (err.status === 401) {
                        localStorage.removeItem('token');
                        this.router.navigate(['/login']);
                    }
                }
                return of(err);
            })
        );
    }
}
