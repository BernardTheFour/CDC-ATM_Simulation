<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transfer - ATM</title>
</head>

<body>
    <h1>Transfer Fund - ATM</h1>
    <hr>
    <form action="${urlversion}/${accountNumber}/transfer" method="post">
        <div class="formbuilder-text form-group field-field-account-number">
            <label for="field-account-number" class="formbuilder-text-label">Receiver Account Number</label></br>
            <input type="number" class="form-control" name="receiverNumber" maxlength="6">
        </div>
        </br>
        <div class="formbuilder-text form-group field-field-pin-number">
            <label for="field-pin-number" class="formbuilder-text-label">Transfer Amount</label></br>
            <input type="number" class="form-control" name="amount" maxlength="6" placeholder="$ 0">
        </div>
        </br>
        <div class="formbuilder-button form-group field-btn-login">
            <button type="submit" class="btn-default btn" name="btnsubmit" id="btn-login">Submit</button>
        </div>
    </form>
    <br>
    <form action="transaction" method="post">
        <button type="submit" class="btn-default btn">Cancel</button>
    </form>
</body>
</html>