<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transfer Summary - ATM</title>
</head>

<body>
    <h1>Transfer Summary - ATM</h1>
    <hr>

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
            <tr>
                <td>Balance </td>
                <td> $ ${balance}</td>
            </tr>
            <tr>
                <td>Status </td>
                <td> ${status}</td>
            </tr>
    </table>

    <div class="navigationbutton">
        </br>
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