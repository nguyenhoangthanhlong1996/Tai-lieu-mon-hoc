import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../services/user.service';
import {MatDialog} from '@angular/material';
import {IUser} from '../../../interfaces/user/IUser';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {CategoryService} from '../../../services/category.service';
import {ICategory} from '../../../interfaces/category/ICategory';

@Component({
    selector: 'app-category-edit',
    templateUrl: './category-edit.component.html',
    styleUrls: ['./category-edit.component.css']
})
export class CategoryEditComponent implements OnInit {

    formCategory: FormGroup;

    constructor(private activatedRoute: ActivatedRoute,
                private categoryService: CategoryService,
                private matDialog: MatDialog,
                private router: Router,
                private formBuilder: FormBuilder) {
    }

    ngOnInit() {
        // Thiết lập form
        this.formCategory = this.formBuilder.group({
            id: [0, Validators.required],
            name: ['', Validators.required],
            note: [''],
        });
        // Lấy thông tin danh mục
        const id = +this.activatedRoute.snapshot.paramMap.get('id');
        this.categoryService.detail(id)
            .subscribe(response => {
                if (response.code === 200) {
                    const categoryDetail: ICategory = response.data;
                    // Gán các giá trị lên form
                    this.formCategory.get('id').setValue(categoryDetail.id);
                    this.formCategory.get('name').setValue(categoryDetail.name);
                    this.formCategory.get('note').setValue(categoryDetail.note);
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
        this.categoryService.update(this.formCategory.value)
            .subscribe(response => {
                if (response.code === 500) {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    const alertData: IAlertDialogData = {content: 'Sửa danh mục thành công', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                    this.router.navigate(['/category-list']);
                }
            }, error => {
                console.log(error);
            });
    }

}
