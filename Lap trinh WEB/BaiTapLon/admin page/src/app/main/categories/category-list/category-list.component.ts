import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {IUserRaw} from '../../../interfaces/user/IUserRaw';
import {Subject} from 'rxjs';
import {DataTableDirective} from 'angular-datatables';
import {UserService} from '../../../services/user.service';
import {MatDialog} from '@angular/material';
import {Router} from '@angular/router';
import {optionsDatable} from '../../../configs/DatatableOptions';
import {IUser} from '../../../interfaces/user/IUser';
import {UserInfoDialogComponent} from '../../users/user-info-dialog/user-info-dialog.component';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {ComfirmDialogComponent} from '../../../comfirm-dialog/comfirm-dialog.component';
import {CategoryService} from '../../../services/category.service';
import {ICategory} from '../../../interfaces/category/ICategory';

@Component({
    selector: 'app-category-list',
    templateUrl: './category-list.component.html',
    styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit, OnDestroy {

    dtOptions: DataTables.Settings = {};
    listCategories: ICategory[] = [];
    dtTrigger: Subject<ICategory> = new Subject();
    @ViewChild(DataTableDirective, {read: DataTableDirective, static: true}) dtElement: DataTableDirective;

    constructor(private categoryService: CategoryService,
                private matDialog: MatDialog,
                private router: Router) {
    }

    rerender(): void {
        if (this.dtElement.dtInstance instanceof Promise) {
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                // Destroy the table first
                dtInstance.destroy();
                this.categoryService.list()
                    .subscribe(response => {
                        if (response.code === 200) {
                            this.listCategories = response.data;
                            // Call the dtTrigger to rerender again
                            this.dtTrigger.next();
                        }
                    }, error => {
                        console.log(error);
                    });

            });
        } else { // Lần đầu render
            this.categoryService.list()
                .subscribe(response => {
                    if (response.code === 200) {
                        this.listCategories = response.data;
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

    edit(id: number) {
        this.router.navigate(['/category-edit', id]);
    }

    remove(id: number) {
        const confirmData: IAlertDialogData = {
            content: 'Bạn có chắc muốn xóa danh mục này ? (Bạn sẽ không thể khôi phục lại)',
            type: DialogType.WARNING
        };
        const dialogRef = this.matDialog.open(ComfirmDialogComponent, {
            data: confirmData
        });
        dialogRef.afterClosed().subscribe(result => {
            if (result === true) {
                this.categoryService.remove(id)
                    .subscribe(response => {
                        if (response.code === 500) {
                            const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                            const dialogRef1 = this.matDialog.open(AlertDialogComponent, {
                                data: alertData
                            });
                        } else if (response.code === 200) {
                            const alertData: IAlertDialogData = {content: 'Xóa danh mục thành công', type: DialogType.SUCESS};
                            const dialogRef1 = this.matDialog.open(AlertDialogComponent, {
                                data: alertData
                            });
                            this.router.navigate(['/category-list']);
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
