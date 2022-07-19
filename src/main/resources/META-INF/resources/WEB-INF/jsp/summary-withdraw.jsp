<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw Summary - ATM</title>
</head>

<body>
    <div class="rendered-form">
        <h3 access="false" id="control-6777127">Withdraw Summary</h3>
        <p access="false" id="control-1178663">Date: ${transactiondate}&nbsp;
            </br>Withdraw: $ ${withdrawfund}
            </br>Balance: $ ${balancefund}
        </p>
    </div>

    <div class="navigationbutton">
        <form action="transaction" method="post">
            <button type="submit" class="btn-default btn">Another Transaction</button>
        </form>
        <br>
        <a href="${urlversion}/">
            <button value="4" type="button" class="btn btn-default" name="btnExit" id="btnExit">Exit</button>
        </a>
    </div>
</body>

</html>