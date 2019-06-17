import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../services/user.service';
import {MatDialog} from '@angular/material';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';

@Component({
    selector: 'app-user-change-password',
    templateUrl: './user-change-password.component.html',
    styleUrls: ['./user-change-password.component.css']
})
export class UserChangePasswordComponent implements OnInit {

    formChangeUserPassword: FormGroup;

    constructor(private activatedRoute: ActivatedRoute,
                private userService: UserService,
                private matDialog: MatDialog,
                private router: Router,
                private formBuilder: FormBuilder) {
    }

    ngOnInit() {
        // Thiết lập form
        this.formChangeUserPassword = this.formBuilder.group({
            username: ['', [Validators.required, Validators.pattern('[a-zA-Z][a-zA-Z0-9]{5,20}')]],
            password: ['', [Validators.required, Validators.pattern('((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})')]],
            retypePassword: ['', [Validators.required]],
        });
        // Gán giá trị username
        const username = this.activatedRoute.snapshot.paramMap.get('username');
        this.formChangeUserPassword.get('username').setValue(username);
    }

    onSubmit(): void {
        this.userService.changePassword(this.formChangeUserPassword.value)
            .subscribe(response => {
                if (response.code === 500) {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    const alertData: IAlertDialogData = {content: 'Đổi mật khẩu thành công', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                    this.router.navigate(['/user-list']);
                }
            }, error => {
                console.log(error);
            });
    }

}
