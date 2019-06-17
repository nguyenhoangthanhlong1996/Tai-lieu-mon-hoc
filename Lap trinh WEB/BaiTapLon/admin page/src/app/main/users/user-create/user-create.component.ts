import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {UserService} from '../../../services/user.service';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {DialogType} from '../../../enums/DialogType';

@Component({
    selector: 'app-user-create',
    templateUrl: './user-create.component.html',
    styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {
    formUser: FormGroup;
    avatarSrc: string;
    currentEventAvatar: any;

    constructor(private router: Router,
                private formBuilder: FormBuilder,
                private userService: UserService,
                private matDialog: MatDialog) {
    }

    ngOnInit() {
        this.avatarSrc = '';
        this.formUser = this.formBuilder.group({
            username: ['', [Validators.required, Validators.pattern('[a-zA-Z][a-zA-Z0-9]{5,20}')]],
            name: ['', Validators.required],
            password: ['', [Validators.required, Validators.pattern('((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})')]],
            retypePassword: ['', [Validators.required]],
            email: ['', [Validators.required, Validators.email]],
            phone: ['', Validators.pattern('[0-9]*')],
            address: [''],
            avatar: ['']
        });
    }

    chooseAvatar(event: any): void {
        this.currentEventAvatar = event;
        const fileList = event.target.files;
        const file: File = fileList.item(0);
        const reader = new FileReader();
        reader.onload = (evt: any) => {
            this.formUser.get('avatar').setValue(evt.target.result);
        };
        reader.readAsDataURL(file);
    }

    removeAvatar() {
        if (this.currentEventAvatar !== undefined) {
            this.currentEventAvatar.srcElement.value = null;
        }
        this.formUser.get('avatar').setValue('');
    }

    onSubmit(): void {
        this.userService.create(this.formUser.value)
            .subscribe(response => {
                if (response.code === 500) {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    this.formUser.reset();
                    this.removeAvatar();
                    const alertData: IAlertDialogData = {content: 'Tạo tài khoản thành công', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                }
            }, error => {
                console.log(error);
            });
    }

}
