import {AfterViewInit, Component, ElementRef, Inject, OnDestroy, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {UserService} from '../../../services/user.service';
import {IUserRaw} from '../../../interfaces/user/IUserRaw';
import {Subject} from 'rxjs';
import {optionsDatable} from '../../../configs/DatatableOptions';
import {IUser} from '../../../interfaces/user/IUser';
import {MatDialog} from '@angular/material';
import {UserInfoDialogComponent} from '../user-info-dialog/user-info-dialog.component';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {Router} from '@angular/router';
import {ComfirmDialogComponent} from '../../../comfirm-dialog/comfirm-dialog.component';
import {DataTableDirective} from 'angular-datatables';

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {

    dtOptions: DataTables.Settings = {};
    listUsers: IUserRaw[] = [];
    dtTrigger: Subject<IUserRaw> = new Subject();
    @ViewChild(DataTableDirective, {read: DataTableDirective, static: true}) dtElement: DataTableDirective;

    constructor(private userService: UserService,
                private matDialog: MatDialog,
                private router: Router) {
    }

    rerender(): void {
        if (this.dtElement.dtInstance instanceof Promise) {
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                // Destroy the table first
                dtInstance.destroy();
                this.userService.list()
                    .subscribe(response => {
                        if (response.code === 200) {
                            this.listUsers = response.data;
                            // Call the dtTrigger to rerender again
                            this.dtTrigger.next();
                        }
                    }, error => {
                        console.log(error);
                    });

            });
        } else { // Lần đầu render
            this.userService.list()
                .subscribe(response => {
                    if (response.code === 200) {
                        this.listUsers = response.data;
                        this.dtTrigger.next();
                    }
                }, error => {
                    console.log(error);
                });
        }
    }

    ngOnInit() {
        // @ts-ignore
        this.dtOptions = optionsDatable;
        this.rerender();
    }

    showUserInfo(username: string) {
        this.userService.detail(username)
            .subscribe(response => {
                if (response.code === 200) {
                    const userInfoData: IUser = response.data;
                    const dialogRef = this.matDialog.open(UserInfoDialogComponent, {
                        data: userInfoData
                    });
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

    changeUserInfo(username: string) {
        this.router.navigate(['/user-change-info', username]);
    }

    changeUserPassword(username: string) {
        this.router.navigate(['/user-change-password', username]);
    }

    removeUser(username: string) {
        const confirmData: IAlertDialogData = {
            content: `Bạn có chắc muốn xóa tài khoản '${username}' ? (Bạn sẽ không thể khôi phục lại)`,
            type: DialogType.WARNING
        };
        const dialogRef = this.matDialog.open(ComfirmDialogComponent, {
            data: confirmData
        });
        dialogRef.afterClosed().subscribe(result => {
            if (result === true) {
                this.userService.remove(username)
                    .subscribe(response => {
                        if (response.code === 500) {
                            const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                            const dialogRef1 = this.matDialog.open(AlertDialogComponent, {
                                data: alertData
                            });
                        } else if (response.code === 200) {
                            const alertData: IAlertDialogData = {content: 'Xóa tài khoản thành công', type: DialogType.SUCESS};
                            const dialogRef1 = this.matDialog.open(AlertDialogComponent, {
                                data: alertData
                            });
                            this.router.navigate(['/user-list']);
                            this.rerender();
                        }
                    }, error => {
                        console.log(error);
                    });
            }
        });
    }

    ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
    }


}
