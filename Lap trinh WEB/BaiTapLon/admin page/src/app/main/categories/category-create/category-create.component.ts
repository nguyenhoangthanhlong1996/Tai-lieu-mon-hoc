import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {CategoryService} from '../../../services/category.service';

@Component({
    selector: 'app-category-create',
    templateUrl: './category-create.component.html',
    styleUrls: ['./category-create.component.css']
})
export class CategoryCreateComponent implements OnInit {

    formCategory: FormGroup;

    constructor(private router: Router,
                private formBuilder: FormBuilder,
                private categoryService: CategoryService,
                private matDialog: MatDialog) {
    }

    ngOnInit() {
        this.formCategory = this.formBuilder.group({
            name: ['', Validators.required],
            note: [''],
        });
    }

    onSubmit(): void {
        this.categoryService.create(this.formCategory.value)
            .subscribe(response => {
                if (response.code === 500) {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    this.formCategory.reset({
                        name: '',
                        note: ''
                    });
                    const alertData: IAlertDialogData = {content: 'Tạo danh mục thành công', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                }
            }, error => {
                console.log(error);
            });
    }

}
