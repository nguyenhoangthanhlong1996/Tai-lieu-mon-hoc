import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../services/user.service';
import {IUser} from '../../../interfaces/user/IUser';
import {UserInfoDialogComponent} from '../user-info-dialog/user-info-dialog.component';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {MatDialog} from '@angular/material';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
    selector: 'app-user-change-info',
    templateUrl: './user-change-info.component.html',
    styleUrls: ['./user-change-info.component.css']
})
export class UserChangeInfoComponent implements OnInit {
    formChangeUserInfo: FormGroup;
    avatarSrc: string;
    currentEventAvatar: any;

    constructor(private activatedRoute: ActivatedRoute,
                private userService: UserService,
                private matDialog: MatDialog,
                private router: Router,
                private formBuilder: FormBuilder) {
    }

    ngOnInit() {
        // Thiết lập form
        this.avatarSrc = '';
        this.formChangeUserInfo = this.formBuilder.group({
            username: ['', [Validators.required, Validators.pattern('[a-zA-Z][a-zA-Z0-9]{5,20}')]],
            name: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            phone: ['', Validators.pattern('[0-9]*')],
            address: [''],
            avatar: ['']
        });
        // Lấy thông tin tài khoản
        const username = this.activatedRoute.snapshot.paramMap.get('username');
        this.userService.detail(username)
            .subscribe(response => {
                if (response.code === 200) {
                    const userDetail: IUser = response.data;
                    // Gán các giá trị lên form
                    this.formChangeUserInfo.get('username').setValue(userDetail.username);
                    this.formChangeUserInfo.get('name').setValue(userDetail.full_name);
                    this.formChangeUserInfo.get('email').setValue(userDetail.email);
                    this.formChangeUserInfo.get('phone').setValue(userDetail.phone);
                    this.formChangeUserInfo.get('address').setValue(userDetail.address);
                    this.formChangeUserInfo.get('avatar').setValue(userDetail.avatar);
                } else if (response.code === 404) {
                    this.router.navigate(['/page-not-found']);
                } else {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                }
            }, error => {
                console.log(error);
            });
    }

    chooseAvatar(event: any): void {
        this.currentEventAvatar = event;
        const fileList = event.target.files;
        const file: File = fileList.item(0);
        const reader = new FileReader();
        reader.onload = (evt: any) => {
            this.formChangeUserInfo.get('avatar').setValue(evt.target.result);
        };
        reader.readAsDataURL(file);
    }

    removeAvatar() {
        if (this.currentEventAvatar) {
            this.currentEventAvatar.srcElement.value = null;
        }
        this.formChangeUserInfo.get('avatar').setValue('');
    }

    onSubmit(): void {
        this.userService.changeInfo(this.formChangeUserInfo.value)
            .subscribe(response => {
                if (response.code === 500) {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    const alertData: IAlertDialogData = {content: 'Sửa thông tin tài khoản thành công', type: DialogType.SUCESS};
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
