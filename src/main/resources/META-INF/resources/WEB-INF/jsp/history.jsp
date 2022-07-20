<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction History - ATM</title>
</head>

<body>

    <h1 access="false" id="control-6777127">Transaction History - ATM</h1>
    <hr>
    <div>
        <table style="border-spacing: 15px 10px" style='border-collapse:separate'>
            <thead>
                <tr>
                    <th>Amount</th>
                    <th>Type</th>
                    <th>From/To</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                ${table}
            </tbody>
        </table>
    </div>

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