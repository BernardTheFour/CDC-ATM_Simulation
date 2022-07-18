<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw - ATM</title>
</head>

<body>
    <div class="">
        <h3 access="false" id="control-9987720">Current Balance: $ ${credit}</h3>
    </div>
    <div class="">
        <h3 access="false" id="control-6741689">Withdraw:</h3>
    </div>

    <div class="rendered-form">
        <div class="formbuilder-button form-group field-btnWithdraw10">
            <form action="${urlversion}/${accountNumber}/withdraw" method="post">
                <button type="submit" class="btn-default btn" name="amount" value="10"
                    id="btnWithdraw10">$10</button>
                <button type="submit" class="btn-default btn" name="amount" value="50"
                    id="btnWithdraw50">$50</button>
                <button type="submit" class="btn-default btn" name="amount" value="100"
                    id="btnWithdraw100">$100</button>
                <button type="submit" class="btn-default btn" name="amount" value="other"
                    id="btnWithdrawOther">Other</button>
            </form>
        </div>
        </br>
        <div class="formbuilder-button form-group field-btnBack">
            <form action="transaction" method="post">
                <button type="submit" class="btn-default btn">Back</button>
            </form>
        </div>
    </div>
</body>

</html>