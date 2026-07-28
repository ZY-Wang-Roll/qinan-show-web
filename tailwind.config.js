/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        warm: {
          50: '#faf8f5',
          100: '#f0ece6',
          200: '#e8e3dc',
          300: '#d9d1c7',
          400: '#b8a89a',
          500: '#9b8b7e',
          600: '#7a6e64',
          700: '#5c534c',
          800: '#3d3732',
          900: '#1a1a1a',
        },
        accent: {
          light: '#d4c4a8',
          DEFAULT: '#c4a682',
          deep: '#a88b6a',
        },
        mist: {
          light: '#b8cfc9',
          DEFAULT: '#8aa4a0',
          deep: '#6b8581',
        },
      },
      fontFamily: {
        display: ['"PingFang SC"', '"Noto Sans SC"', '"Helvetica Neue"', 'system-ui', 'sans-serif'],
        body: ['"PingFang SC"', '"Noto Sans SC"', '"Helvetica Neue"', 'system-ui', 'sans-serif'],
        number: ['"SF Mono"', '"JetBrains Mono"', 'monospace'],
      },
      letterSpacing: {
        ultra: '0.25em',
        mega: '0.5em',
      },
      screens: {
        'xs': '480px',
      },
    },
  },
  plugins: [],
}
