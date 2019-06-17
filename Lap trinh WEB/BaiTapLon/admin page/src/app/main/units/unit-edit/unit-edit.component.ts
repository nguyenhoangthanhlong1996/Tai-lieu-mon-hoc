import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../../services/category.service';
import {MatDialog} from '@angular/material';
import {ICategory} from '../../../interfaces/category/ICategory';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {UnitService} from '../../../services/unit.service';
import {IUnit} from '../../../interfaces/unit/IUnit';

@Component({
    selector: 'app-unit-edit',
    templateUrl: './unit-edit.component.html',
    styleUrls: ['./unit-edit.component.css']
})
export class UnitEditComponent implements OnInit {

    formUnit: FormGroup;

    constructor(private activatedRoute: ActivatedRoute,
                private unitService: UnitService,
                private matDialog: MatDialog,
                private router: Router,
                private formBuilder: FormBuilder) {
    }

    ngOnInit() {
        // Thiết lập form
        this.formUnit = this.formBuilder.group({
            id: [0, Validators.required],
            name: ['', Validators.required],
            note: [''],
        });
        // Lấy thông tin đơn vị tính
        const id = +this.activatedRoute.snapshot.paramMap.get('id');
        this.unitService.detail(id)
            .subscribe(response => {
                if (response.code === 200) {
                    const unitDetail: IUnit = response.data;
                    // Gán các giá trị lên form
                    this.formUnit.get('id').setValue(unitDetail.id);
                    this.formUnit.get('name').setValue(unitDetail.name);
                    this.formUnit.get('note').setValue(unitDetail.note);
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

    onSubmit(): void {
        this.unitService.update(this.formUnit.value)
            .subscribe(response => {
                if (response.code === 500) {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    const alertData: IAlertDialogData = {content: 'Sửa đơn vị tính thành công', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                    this.router.navigate(['/unit-list']);
                }
            }, error => {
                console.log(error);
            });
    }

}
