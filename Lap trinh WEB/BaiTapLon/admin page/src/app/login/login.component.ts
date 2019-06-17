import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {MatDialog} from '@angular/material';
import {AlertDialogComponent} from '../alert-dialog/alert-dialog.component';
import {IAlertDialogData} from '../interfaces/IAlertDialogData';
import {Router} from '@angular/router';
import {DialogType} from '../enums/DialogType';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    formLogin: FormGroup;

    constructor(private router: Router,
                private formBuilder: FormBuilder,
                private authService: AuthService,
                private matDialog: MatDialog) {
    }

    ngOnInit(): void {
        this.formLogin = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
    }

    onSubmit() {
        this.authService.login(this.formLogin.value)
            .subscribe(response => {
                if (response.code === 401) {
                    const alertData: IAlertDialogData = {content: 'Tên tài khoản hoặc mật khẩu không đúng', type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    localStorage.setItem('token', response.data.token);
                    this.router.navigate(['/']);
                }
            }, error => {
                console.log(error);
            });
    }
}
