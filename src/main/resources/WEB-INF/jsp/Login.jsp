<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Automated Teller Machine</title>
</head>

<body>
        <div>
            <div>
                <h1 id="control-3973373">Automated Teller Machine</h1>
            </div>

            <div>
                <h3 id="control-1003910">Insert your credentials</h3>
            </div>

            <div class="formbuilder-text form-group field-field-account-number">
                <label for="field-account-number" class="formbuilder-text-label">Account Number</label></br>
                <input type="text" class="form-control" name="field-account-number" maxlength="6"
                    id="field-account-number">
            </div>
            </br>
            <div class="formbuilder-text form-group field-field-pin-number">
                <label for="field-pin-number" class="formbuilder-text-label">PIN Number</label></br>
                <input type="password" class="form-control" name="field-pin-number" maxlength="6" id="field-pin-number">
            </div>
            </br>
            <div class="formbuilder-button form-group field-btn-login">
                <button type="submit" class="btn-default btn" name="btn-login" id="btn-login">Login</button>
            </div>
        </div>

        <%
            String name = request.getParameter("field-account-number");
            out.print("Print" + name); 
        %>
</body>

</html>