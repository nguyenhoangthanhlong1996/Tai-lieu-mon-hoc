{{-- Dialog thông báo xóa --}}
<div class="dialog-delete">
	<div class="modal-dialog">
		<div class="title">
			<h5>Thông báo</h5>
		</div>
		<div class="content">
			<p>{{$content}}</p>
			<div class="button-confirm">
				<button class="btn btn-danger btn-accept">Xóa</button>
				<button class="btn btn-info btn-cancel">Hủy</button>
			</div>
		</div>
	</div>
</div>

<style>
	.dialog-delete {
		position: fixed;
		/* Positioning and size */
		top: 0;
		left: 0;
		width: 100vw;
		height: 100vh;
		background-color: rgba(128, 128, 128, 0.5);
		/* color */
		display: none;
		/* making it hidden by default */
	}

	.modal-dialog {
		width: 300px;
		height: 200px;
		max-width: 400px;
		border: 1px solid black;
		border-radius: 10px;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		overflow: hidden;
		background-color: white;
	}

	.modal-dialog>.title {
		border-bottom: 1px solid black;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-between;
		background-color: #002171;
		padding: 0 5px;
	}

	.modal-dialog>.title>h5 {
		color: white;
	}

	.modal-dialog>.title>button {
		color: red;
		background-color: transparent;
		border: none;
		padding: 0;
	}

	.modal-dialog>.content {
		padding: 5px 10px;
		height: 70%;
		font-family: 'Open Sans', sans-serif;
		font-size: 16px;
		color: black;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}

	.modal-dialog>.content .button-confirm {
		display: flex;
		justify-content: flex-end;
	}

	.modal-dialog>.content .button-confirm button {
		margin-left: 10px;
	}
</style>