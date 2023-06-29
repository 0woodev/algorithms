def infix2postfix(expression, op_priority):

    stack = []
    postfix = []

    for c in expression:
        if (c != "+") and (c!= "-") and (c != "*"):
            postfix.append(int(c))

        elif not stack:
            stack.append(c)

        elif op_priority[c] > op_priority[stack[-1]]:
            stack.append(c)

        else:
            while stack and op_priority[c] <= op_priority[stack[-1]]:
                postfix.append(stack.pop())

            stack.append(c)

    while stack:
        postfix.append(stack.pop())

    return postfix

def calculate(postfix):

    stack = []

    for c in postfix:
        if (c != "+") and (c!= "-") and (c != "*"):
            stack.append(c)
            continue

        b = stack.pop()
        a = stack.pop()

        if c == "+":
            stack.append(a + b)
        elif c == "-":
            stack.append(a - b)
        else:
            stack.append(a * b)

    result = stack.pop()

    return abs(result)

def solution(expression):

    expression = expression.replace('"', '')
    expression = expression.replace('+', ' + ')
    expression = expression.replace('-', ' - ')
    expression = expression.replace('*', ' * ')
    expression = expression.split()

    pri = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

    result = []

    for i in range(6):

        op_priority = dict(zip(['+', '-', '*'], pri[i]))
        postfix = infix2postfix(expression, op_priority)
        result.append(calculate(postfix))

    return max(result)