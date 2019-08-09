# Returns input number with each each digit incremented
def upVal(val):
    # Incrementing single digit number is just plus one
    if val < 10:
        return val + 1
    
    # Mod to extract decimal places of value, and 
    dig = 1
    result = val
    while pow(10, dig) < (result * 10):
        mod = pow(10, dig)
        temp = result % mod
        
        if temp < (mod * 0.9):
            result = result + pow(10, (dig - 1))
            dig += 1
        else:
            upper = (result - (result % mod)) * 10
            if dig == 1:
                result = upper + mod
            else:
                result = upper + mod + (result % (mod/10))
            dig += 2

    return result

n = int(input("Input value? "))
print(upVal(n))
