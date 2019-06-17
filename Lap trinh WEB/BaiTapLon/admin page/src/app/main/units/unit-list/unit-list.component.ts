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
import {IUnit} from '../../../interfaces/unit/IUnit';
import {UnitService} from '../../../services/unit.service';

@Component({
    selector: 'app-unit-list',
    templateUrl: './unit-list.component.html',
    styleUrls: ['./unit-list.component.css']
})
export class UnitListComponent implements OnInit, OnDestroy {

    dtOptions: DataTables.Settings = {};
    listUnits: IUnit[] = [];
    dtTrigger: Subject<IUnit> = new Subject();
    @ViewChild(DataTableDirective, {read: DataTableDirective, static: true}) dtElement: DataTableDirective;

    constructor(private unitService: UnitService,
                private matDialog: MatDialog,
                private router: Router) {
    }

    rerender(): void {
        if (this.dtElement.dtInstance instanceof Promise) {
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                // Destroy the table first
                dtInstance.destroy();
                this.unitService.list()
                    .subscribe(response => {
                        if (response.code === 200) {
                            this.listUnits = response.data;
                            // Call the dtTrigger to rerender again
                            this.dtTrigger.next();
                        }
                    }, error => {
                        console.log(error);
                    });

            });
        } else { // Lần đầu render
            this.unitService.list()
                .subscribe(response => {
                    if (response.code === 200) {
                        this.listUnits = response.data;
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
        this.router.navigate(['/unit-edit', id]);
    }

    remove(id: number) {
        const confirmData: IAlertDialogData = {
            content: 'Bạn có chắc muốn xóa đơn vị tính này ? (Bạn sẽ không thể khôi phục lại)',
            type: DialogType.WARNING
        };
        const dialogRef = this.matDialog.open(ComfirmDialogComponent, {
            data: confirmData
        });
        dialogRef.afterClosed().subscribe(result => {
            if (result === true) {
                this.unitService.remove(id)
                    .subscribe(response => {
                        if (response.code === 500) {
                            const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                            const dialogRef1 = this.matDialog.open(AlertDialogComponent, {
                                data: alertData
                            });
                        } else if (response.code === 200) {
                            const alertData: IAlertDialogData = {content: 'Xóa đơn vị tính thành công', type: DialogType.SUCESS};
                            const dialogRef1 = this.matDialog.open(AlertDialogComponent, {
                                data: alertData
                            });
                            this.router.navigate(['/unit-list']);
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
