import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {Page404Component} from './page404/page404.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {TokenInterceptorService} from './services/token-interceptor.service';
import {LoginModule} from './login/login.module';
import {MainModule} from './main/main.module';

@NgModule({
    declarations: [
        AppComponent,
        Page404Component
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        LoginModule,
        MainModule
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptorService,
            multi: true
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {}
