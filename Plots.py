import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv('Patience/results.csv')
plt.figure(figsize=(12,5))

plt.subplot(1,2,1)
plt.plot(data['Size'], data['Time(ms)'])
plt.title('Time complexity')

plt.subplot(1,2,2)
plt.plot(data['Size'], data['Iterations'])
plt.title('Iterations count')

plt.show()