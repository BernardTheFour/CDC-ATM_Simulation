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
    <form action="${urlversion}/${acccountNumber}/transfer-confirm" method="post">
        <table style="border-spacing: 15px 10px" style='border-collapse:separate'>
            <tr>
                <td>Destination Account</td>
                <td>${destination}</td>
            </tr>
            <tr>
                <td>Transfer Amount </td>
                <td> ${amount}</td>
            </tr>
            <tr>
                <td>Reference Number </td>
                <td> ${reference}</td>
            </tr>
        </table>

        </br>
        <div class="formbuilder-button form-group field-btn-login">
            <button type="submit" class="btn-default btn" name="btnsubmit" id="btn-login">Confirm</button>
        </div>
    </form>
    <br>
    <a href="transaction">
        <button type="button"> Cancel</button>
    </a>
</body>

</html>