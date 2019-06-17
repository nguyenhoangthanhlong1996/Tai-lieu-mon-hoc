import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Subject} from 'rxjs';
import {DataTableDirective} from 'angular-datatables';
import {MatDialog} from '@angular/material';
import {Router} from '@angular/router';
import {optionsDatable} from '../../../configs/DatatableOptions';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {ComfirmDialogComponent} from '../../../comfirm-dialog/comfirm-dialog.component';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {ProductService} from '../../../services/product.service';
import {IProductRaw} from '../../../interfaces/product/IProductRaw';
import {IUser} from '../../../interfaces/user/IUser';
import {UserInfoDialogComponent} from '../../users/user-info-dialog/user-info-dialog.component';
import {IProduct} from '../../../interfaces/product/IProduct';
import {ProductInfoDialogComponent} from '../product-info-dialog/product-info-dialog.component';

@Component({
    selector: 'app-product-list',
    templateUrl: './product-list.component.html',
    styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit, OnDestroy {

    dtOptions: DataTables.Settings = {};
    listProducts: IProductRaw[] = [];
    dtTrigger: Subject<IProductRaw> = new Subject();
    @ViewChild(DataTableDirective, {read: DataTableDirective, static: true}) dtElement: DataTableDirective;

    constructor(private productService: ProductService,
                private matDialog: MatDialog,
                private router: Router) {
    }

    rerender(): void {
        if (this.dtElement.dtInstance instanceof Promise) {
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                // Destroy the table first
                dtInstance.destroy();
                this.productService.list()
                    .subscribe(response => {
                        if (response.code === 200) {
                            this.listProducts = response.data;
                            // Call the dtTrigger to rerender again
                            this.dtTrigger.next();
                        }
                    }, error => {
                        console.log(error);
                    });

            });
        } else { // Lần đầu render
            this.productService.list()
                .subscribe(response => {
                    if (response.code === 200) {
                        this.listProducts = response.data;
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

    detail(id: number) {
        this.productService.detail(id)
            .subscribe(response => {
                if (response.code === 200) {
                    const productInfoData: IProduct = response.data;
                    console.log(productInfoData);
                    const dialogRef = this.matDialog.open(ProductInfoDialogComponent, {
                        data: productInfoData
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

    edit(id: number) {
        this.router.navigate(['/product-edit', id]);
    }

    remove(id: number) {
        const confirmData: IAlertDialogData = {
            content: 'Bạn có chắc muốn xóa sản phẩm này ? (Bạn sẽ không thể khôi phục lại)',
            type: DialogType.WARNING
        };
        const dialogRef = this.matDialog.open(ComfirmDialogComponent, {
            data: confirmData
        });
        dialogRef.afterClosed().subscribe(result => {
            if (result === true) {
                this.productService.remove(id)
                    .subscribe(response => {
                        if (response.code === 500) {
                            const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                            const dialogRef1 = this.matDialog.open(AlertDialogComponent, {
                                data: alertData
                            });
                        } else if (response.code === 200) {
                            const alertData: IAlertDialogData = {content: 'Xóa sản phẩm thành công', type: DialogType.SUCESS};
                            const dialogRef1 = this.matDialog.open(AlertDialogComponent, {
                                data: alertData
                            });
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
