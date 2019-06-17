import {Component, OnInit} from '@angular/core';
import {AuthService} from '../services/auth.service';

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

    fullName: string;
    avatar: string;


    constructor(private authService: AuthService) {
    }

    ngOnInit() {
        this.fullName = 'unknow';
        this.avatar = 'assets/dist/img/unknow_user.png';
        this.authService.getUserInfo()
            .subscribe(response => {
                if (response.code === 200) {
                    const data = response.data;
                    this.fullName = data.full_name;
                    this.avatar = data.avatar;
                }
            }, error => {
                console.log(error);
            });
    }

    onLogout() {
        this.authService.logout();
    }
}
